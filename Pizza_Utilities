/*
 * for loop
 */

for(V,V,_,_) :- !.
for(Start,End,Inc,Action) :-
    End > Start,
    NewValue is Start+Inc,
    call(Action),
    for(NewValue,End,Inc,Action).


/**
 * first_elements
 */
first_elements([], BoardsList, BoardsList):-!.

first_elements([First|Rest], Temp, Boards):-
	nth0(0, First, Board),
	append(Temp, [Board], NTemp),
	first_elements(Rest, NTemp, Boards).

/**
 * first_n_elements
 */
first_n_elements(Number, List, NList):-
		length(List, N),
		N =< Number,
		List = NList,!.

first_n_elements(Number, List, NList):-
	first_n_elements_aux(Number, List, [], NList).
	
first_n_elements_aux(0, _, NList, NList):-!.

first_n_elements_aux(Number, [First|Rest], TempList, NList):-
	NNumber is Number - 1,
	append(TempList, [First], NTempList),
	first_n_elements_aux(NNumber, Rest, NTempList, NList).
	
/**
 * min_list
 */
min_list([First|Rest], Min):-
	min_list_aux(Rest, First, Min).

min_list_aux([], Min, Min):-!.

min_list_aux([First|Rest], CurrentMin, Min):-
	First < CurrentMin,
	min_list_aux(Rest, First, Min),!.

min_list_aux([_|Rest], CurrentMin, Min):-
	min_list_aux(Rest, CurrentMin, Min),!.

/**
 * max_list
 */
max_list([First|Rest], Max):-
	max_list_aux(Rest, First, Max).

max_list_aux([], Max, Max):-!.

max_list_aux([First|Rest], CurrentMax, Max):-
	First > CurrentMax,
	max_list_aux(Rest, First, Max),!.

max_list_aux([_|Rest], CurrentMax, Max):-
	max_list_aux(Rest, CurrentMax, Max),!.


max(A,B,Max):-
	A >= B->
		Max=A
	;
	Max=B.
