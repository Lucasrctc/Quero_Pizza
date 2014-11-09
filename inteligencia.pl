escolhe(Board, Color, FinalBoard):-
    find_states(Color, Board, Color, BoardList),
    escolhe(BoardList, Color, 1000, -1000, 0, 0, 0, Index),
    get(BoardList, Index, FinalBoard).

escolhe([], Color, _, _, Imin, Imax, _, Index):-
    (
        Color = white->
            Index = Imin
        ;   
            Index = Imax
    ).


escolhe([X|RemBoards], Color, MinValue, MaxValue, Imin, Imax, CurIndex, Index):-
    eval(X, V),
    (
        V> MaxValue ->
            NMaxValue is V, 
            NImax is CurIndex
        ;
            NMaxValue is MaxValue,
            NImax is Imax
    ),
    (
        V< MinValue ->
            NMinValue is V,
            NImin is CurIndex
        ;
            NMinValue is MinValue,
            NImin is Imin
    ),
    NCurIndex is CurIndex + 1,
    escolhe(RemBoards, Color, NMinValue, NMaxValue, NImin, NImax, NCurIndex, Index).

get([Elemento|_], 0, Resposta):-
    Resposta = Elemento.

get([_|Restante], Indice, Resposta):-
    NIndice is Indice-1,
    get(Restante, NIndice, Resposta).