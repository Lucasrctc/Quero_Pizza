play:-
	asserta(rownum(8)),
	asserta(colnum(8)),
	init_board(0,[],Board),
	asserta(loop_board(Board)),
	print_board(Board),
	asserta(loop_color(black)).

play:-
	destroy.

destroy:-
	rownum(Rown),
	colnum(Coln),
	retract(rownum(Rown)),
	retract(colnum(Coln)).

loop_assign(Board, Color):-
	asserta(loop_board(Board)),
	asserta(loop_color(Color)).

loop_retract(Board, Color):-
	retract(loop_board(Board)),
	retract(loop_color(Color)).

loop_start(Board, Color):-
	loop_board(Board),
	loop_color(Color).

game_loop(X, Y, Finished):-
    loop_start(Board, Color),
    is_board_full(Board,Finished),
    (
        Finished = no ->
		find_moves(Board, Color, MovesList),
		member(_, MovesList),
		rival_color(Color,RivalColor),
	    print_player(Color),
		(
			
				Color = black ->
					human_select_move(X, Y, Move, MovesList),!,
					set_piece(Board, Move, Color, FinalBoard),
					loop_retract(Board, Color),
					loop_assign(FinalBoard, RivalColor),
					print_board(FinalBoard),!
				;
					/*machine_select_move(Board, Depth, white, FinalBoard),!,*/
					machine_select_move(Board, Color, FinalBoard),
					loop_retract(Board, Color),
					loop_assign(FinalBoard, RivalColor),
	    			print_board(FinalBoard),!
		)
		;
		show_statistics(Board)
    ).

game_loop(_,_, Finished):-
	loop_board(Board),
	loop_color(Color),
	find_moves(Board, Color, MovesList),!,
	not(member(_,MovesList)),!,
    rival_color(Color, RivalColor),
	(
        /* if rival also have no move, show statistics*/
        (find_moves(Board, RivalColor, RivalMovesList), member(_,RivalMovesList))->	
        	Finished = no,
		    writeln('There\'s no valid move.'),
			retract(loop_color(Color)),
			asserta(loop_color(RivalColor)),!
		;
            writeln('There\'s no valid move for both players.'),
            Finished = yes,
            show_statistics(Board)
	).

show_statistics(Board):-
    nl,
    count_pieces(black, Board, NumBlack, NumWhite),
    writef('black: %d\n',[NumBlack]),
    writef('white: %d\n',[NumWhite]),
    abs(NumBlack-NumWhite, Diff),
    (
        NumBlack > NumWhite ->
            writef('black win %d piece\n', [Diff])
        ;
        NumBlack < NumWhite ->
            writef('white win %d piece\n', [Diff])
        ;
        write('Tie game\n')

    ),
    nl.

print_player(white):-
    nl,
	writeln('White player''s turn (0)'),!.

print_player(black):-
    nl,
	writeln('Black player''s turn (X)'),!.

human_select_move(SelectedRow, SelectedColum, Move, MovesList):-
	member(Move, MovesList),
	nth0(0, Move, SelectedRow),
	nth0(1, Move, SelectedColum).

/*machine_select_move(Board, Depth, Color, FinalBoard):-
	garbage_collect,
	alpha_beta_pruning(Board, Depth, Color, FinalBoard, _).*/

machine_select_move(Board, Color, FinalBoard):-
	escolhe(Board, Color, FinalBoard).

/**
 * rival_color
 */
rival_color(white, black).
rival_color(black, white).
