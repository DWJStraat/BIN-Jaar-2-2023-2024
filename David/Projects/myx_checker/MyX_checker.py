import time

import disnake
from disnake.ext import commands
import json
import requests

command_sync_flags = commands.CommandSyncFlags.default()

config = json.load(open("config.json"))

bot = commands.Bot(
    command_prefix=config["prefix"],
    intents=disnake.Intents.default()
)


@bot.event
async def on_ready():
    print("Logged on as", bot.user)

@bot.slash_command(description="Run")
async def run(ctx):
    getting_status = True
    while getting_status:
        status = requests.get("https://myx-han.xedule.nl").status_code
        if status == 200:
            print("Status OK")
            await bot.change_presence(status=disnake.Status.online,
                                      activity=disnake.Game(
                                          name="Microsoft Excel"))
            getting_status = False
        else:
            print("Status not OK, retrying...")
            await bot.change_presence(status=disnake.Status.dnd,
                                      activity=disnake.Activity(
                                          name="MyX is down :(",
                                          type=disnake.ActivityType.watching,
                                          url="https://myx-han.xedule.nl",
                                          state="Status code: " + str(
                                              status)
                                      ))
        time.sleep(60)

bot.run(config["token"])
