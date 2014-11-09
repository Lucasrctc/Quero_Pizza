eval(Board, Value):-
	p_color(black);p_color(white),
    count_pieces(black, Board, BlackPieces, WhitePieces),
    PieceDif is BlackPieces - WhitePieces,
    dif(Dificuldade),
    (
    	Dificuldade = 1 ->
	    	Value is PieceDif
    	;
    	Dificuldade = 2 ->
    		getEdges(Edges),
    		positionCount(black,Board,Edges,BlackEdges,WhiteEdges),
    		EdgeDif is 10 * (BlackEdges -  WhiteEdges),
    		Value is PieceDif + EdgeDif
    	;
    	getEdges(Edges),
    	positionCount(black,Board,Edges,BlackEdges,WhiteEdges),
    	EdgeDif is 10 * (BlackEdges -  WhiteEdges),
    	getCorners(Corners),
    	positionCount(black,Board,Corners,BlackCorners,WhiteCorners),
    	CornerDif is 80 * (BlackCorners -  WhiteCorners),
    	Dificuldade = 3 ->
    		Bonus is EdgeDif + CornerDif,
    		Value is PieceDif + Bonus
    ).


eval(Board, Value):-
    count_pieces(black, Board, BlackPieces, WhitePieces),
    PieceDif is BlackPieces - WhitePieces,
    loop_color(Color),
    (
    	Color = black ->
    		difP(Dificuldade)
    	;
	    	difB(Dificuldade)
    ),
    (
    	Dificuldade = 1 ->
	    	Value is PieceDif
    	;
    	Dificuldade = 2 ->
    		getEdges(Edges),
    		positionCount(black,Board,Edges,BlackEdges,WhiteEdges),
    		EdgeDif is 10 * (BlackEdges -  WhiteEdges),
    		Value is PieceDif + EdgeDif
    	;
    	getEdges(Edges),
    	positionCount(black,Board,Edges,BlackEdges,WhiteEdges),
    	EdgeDif is 10 * (BlackEdges -  WhiteEdges),
    	getCorners(Corners),
    	positionCount(black,Board,Corners,BlackCorners,WhiteCorners),
    	CornerDif is 80 * (BlackCorners -  WhiteCorners),
    	Dificuldade = 3 ->
    		Bonus is EdgeDif + CornerDif,
    		Value is PieceDif + Bonus
    ).
    
/*count pieces at special position*/
positionCount(Color,Board,PositionList,Count,RivalCount):-
	positionCount(Color,Board,PositionList,0,0,Count,RivalCount).

positionCount(Color,Board,PositionList,CountBuf,RivalCountBuf,Count,RivalCount):-
	rival_color(Color,RivalColor),
	(
		PositionList=[]->
			Count=CountBuf,
			RivalCount=RivalCountBuf
		;
		PositionList = [Position|PositionsRest],
		Position = [Rowi,Coli|CheckList],
		(
			CheckList\=[]->
				CheckList=[CheckRow,CheckCol],
				piece(Board,CheckRow,CheckCol,CheckPiece)
			;
				CheckPiece=null
		),
		piece(Board,Rowi,Coli,Piece),
		(
			(Piece=Color,CheckPiece\=Color)->
				NCountBuf is CountBuf+1,
				NRivalCountBuf is RivalCountBuf
			;
			(Piece=RivalColor,CheckPiece\=RivalColor)->
				NCountBuf is CountBuf,
				NRivalCountBuf is RivalCountBuf +1
			;
				NCountBuf is CountBuf,
				NRivalCountBuf is RivalCountBuf
		),
		positionCount(Color,Board,PositionsRest,NCountBuf,NRivalCountBuf,Count,RivalCount)
	).

getCorners(Corners):-
	getRowCol(RR,CC),
	R is RR-1,
	C is CC-1,
	Corners=[[0,0],[0,C],
			 [R,0],[R,C]].

getEdges(Edges):-
	Edges = [[0,0],[0,1],[0,2],[0,3],[0,4],[0,5],[0,6],[0,7],
			 [1,0],									   [1,7],
			 [2,0],									   [2,7],
			 [3,0],									   [3,7],
			 [4,0],									   [4,7],
			 [5,0],									   [5,7],
			 [6,0],									   [6,7],
			 [7,0],[7,1],[7,2],[7,3],[7,4],[7,5],[7,6],[7,7]].