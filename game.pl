/*Escolhe aqui a quantidade de jogadas a ver na frente*/
play(Depth):-
    asserta(loop_depth(Depth)),
    play(Depth,8,8).

play(Depth,Rown,Coln):-
    /*init variável global*/
    (
	asserta(rownum(Rown)),
	asserta(colnum(Coln)),
	init_board(0,[],Board),
	select_mode(Mode),
	asserta(loop_mode(Mode)),
	(
		Mode is 5 ->
        	game_loop(Board,4,Depth,white)
        ;
               	game_loop(Board,Mode,Depth,black)
	),
	/*clean variável global*/
	destroy
    ),!.

/*No caso de falha do metodo play por qualquer motivo, pode ser que não se retirem as variaveis globais rownum e colnum (tamanho do tabuleiro)*/
play(_,_,_):-
	destroy.

destroy:-
	rownum(Rown),
	colnum(Coln),
	retract(rownum(Rown)),
	retract(colnum(Coln)).


/*
 * Relation: game_loop/4
 * Loops the game, switching turns, until the game ends
 * @1: Board - the current board
 * @2: Mode - the game mode.
 *	1 for human vs machine
 *	2 for machine vs human
 *	3 for human vs human
 * @3: Depth - the maximum depth of the search
 * @4: Color - the color of the player that moves next
 */
game_loop:-
    loop_board(Board),
    loop_mode(Mode),
    loop_depth(Depth),
    loop_color(Color),
    is_board_full(Board,IsBoardFull),
    (
        IsBoardFull = yes ->
            show_statistics(Board, NumBlack, NumWhite)
        ;
		find_moves(Board, Color, MovesList),
		member(_, MovesList),			/*Se nao ha movimentos validos, nao unifica!*/
		rival_color(Color,RivalColor),
		(
			Mode = 1 ->
			(
				Color = black ->
					human_select_move(Move, MovesList),!,
					set_piece(Board, Move, Color, FinalBoard),
					retract(loop_board(Board)),
					retract(loop_color(Color)),
					asserta(loop_board(FinalBoard)),
					asserta(loop_color(RivalColor)),!
				;
				Color = white ->
					machine_select_move(Board, Depth, white, FinalBoard),!,
					retract(loop_board(Board)),
					retract(loop_color(Color)),
					asserta(loop_board(FinalBoard)),
					asserta(loop_color(RivalColor)),!
			)
			;
			Mode = 2 ->
			(
				Color = black ->
					machine_select_move(Board, Depth, Color, FinalBoard),!,
					retract(loop_board(Board)),
					retract(loop_color(Color)),
					asserta(loop_board(FinalBoard)),
					asserta(loop_color(RivalColor)),!
				;
				Color = white ->
					human_select_move(Move, MovesList),!,
					set_piece(Board, Move, Color, FinalBoard),
					retract(loop_board(Board)),
					retract(loop_mode(Mode)),
					asserta(loop_board(FinalBoard)),
					asserta(loop_color(RivalColor)),!
			)
			;
			Mode = 3 ->
				human_select_move(Move, MovesList),!,
				set_piece(Board, Move, Color, FinalBoard),
				retract(loop_board(Board)),
				retract(loop_mode(Mode)),
				asserta(loop_board(FinalBoard)),
				asserta(loop_color(RivalColor)),!
			;
			Mode = 4 ->
				machine_select_move(Board, Depth, Color, FinalBoard),!,
				retract(loop_board(Board)),
				retract(loop_mode(Mode)),
				asserta(loop_board(FinalBoard)),
				asserta(loop_color(RivalColor)),!
		)
    ).

game_loop:-
	loop_board(Board),
	loop_mode(Mode),
	loop_depth(Depth),
	loop_color(Color),
	find_moves(Board, Color, MovesList),!,
	not(member(_,MovesList)),!,
    rival_color(Color, RivalColor),
	(
        /* if rival also have no move, show statistics*/
        (find_moves(Board, RivalColor, RivalMovesList), member(_,RivalMovesList))->	
		    writeln('There\'s no valid move.'),
			game_loop(Board, Mode, Depth, RivalColor),!
		;
            writeln('There\'s no valid move for both players.'),
            show_statistics(Board, NumBlack, NumWhite)
	).

show_statistics(Board, NumBlack, NumWhite):-
    count_pieces(black, Board, NumBlack, NumWhite).

/*
 * Relation: human_select_move/2
 * Succeds when Move unifies with one of the possible moves in MovesList
 * @1: Move - The move selected by the human
 * @2: MovesList - The list of possible moves
 */
human_select_move(Move, MovesList):-
	read(SelectedRow),
	read(SelectedColum),            /*Caraca, que foda*/
	member(Move, MovesList),	/*unifica Move com todos os elementos da Moveslist, que sao uma lista com 2 inteiros*/
	nth0(0, Move, SelectedRow),	/*Essas 2 linhas unificam Move com [SelectedRow,SelectedColumn]*/
	nth0(1, Move, SelectedColum).

human_select_move(Move, MovesList):-
	human_select_move(Move, MovesList).

/*
 * Relation: machine_select_move/4
 * Selects a move from the possible ones for the player that has the next move
 * @1: Board - The current board
 * @2: Depth - The maximum depth of the search
 * @3: Color - The color of the player that moves next
 * @4: FinalBoard - The board after applying the move selected by the machine
 */
machine_select_move(Board, Depth, Color, FinalBoard):-
	garbage_collect,
	alpha_beta_pruning(Board, Depth, Color, FinalBoard, _).

/*
 * select_mode & enter_mode
 */
select_mode(Mode):-
/*Melhorar aqui...
1. human vs machine
2. machine vs human
3. human vs human
4. machine vs machine (black first)
5. machine vs machine (white first)
*/
	read(SelectedMode),
	(
        SelectedMode is 1 ->
		    Mode is SelectedMode,!
        ;
        SelectedMode is 2 ->
		    SelectedMode is 2,
		    Mode is SelectedMode,!
        ;
        SelectedMode is 3 ->
            Mode is SelectedMode,!
        ;
        SelectedMode is 4 ->
            Mode is SelectedMode,!
        ;
        SelectedMode is 5 ->
            Mode is SelectedMode,!
        ;
		    select_mode(Mode)
	).

/**
 * rival_color
 */
rival_color(white, black).
rival_color(black, white).

