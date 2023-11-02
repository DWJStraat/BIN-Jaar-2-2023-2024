class Board():
    def __init__(self, list_of_fields):
        self.fields = list_of_fields
        self.board = self.create_board()
        self.victory = False
        self.owner = ""

    def create_board(self):
        return [self.fields[i*3:i*3+3] for i in range(3)]

    def check_victory(self):
        for row in self.board:
            if row[0] == row[1] == row[2] != ' ':
                return True
        return next(
            (
                True
                for i in range(3)
                if self.board[0][i] == self.board[1][i] == self.board[2][i] != ' '
            ),
            True
            if self.board[0][0] == self.board[1][1] == self.board[2][2] != ' '
            else self.board[0][2] == self.board[1][1] == self.board[2][0] != ' ',
        )

    def check_draw(self):
        return all(field != ' ' for row in self.board for field in row)

    def make_move(self, x_pos, y_pos, player):
        target = self.board[y_pos][x_pos]
        if isinstance(target, str):
            self.board[y_pos][x_pos] = player
        else:
            
        if self.check_victory():
            self.victory = True
            self.owner = player
        elif self.check_draw():
            self.victory = True
            self.owner = "draw"