import discord
import json

config = json.load(open('config.json'))


class myClient(discord.Client):
    async def on_ready(self):
        print('Logged on as', self.user)
        invite = input("Enter invite code: ")
        try:
            invite = await self.fetch_invite(invite)
            print(f'Invite {invite.code} is valid')
            print(f'Name:{invite.guild.name}')
            print(f'ID:{invite.guild.id}')
            print(f'Members:{invite.approximate_member_count}')
            print(f'Online:{invite.approximate_presence_count}')
            print(f'Temporary invite? {invite.temporary}')
            self.invite = invite
        except:
            print("Invalid invite")

        await self.close()


intents = discord.Intents.default()
intents.members = True
client = myClient(intents=intents)
client.run(config['token'])
