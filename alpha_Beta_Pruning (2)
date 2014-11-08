/*
* Relation: alpha_beta_pruning/5
* Searches for a move using the alpha-beta pruning algorithm
* @1: State - the current board
* @2: Depth - the maximum depth of the search
* @3: Color - the color of the player that moves next
* @4: NewState - the board after applying the move selected by the search
* @5: Value - the heuristic value of the move
*/
alpha_beta_pruning(State, Depth, Color, NewState, Value):-
alpha_beta_pruning(Color,Depth, State, Color, NewState, Value, -100000, 100000).

/*
* Relation: alpha_beta_pruning/7
* Searches for a move using the alpha-beta pruning algorithm with an alpha and a beta value
*/
alpha_beta_pruning(Caller,_, State, _, State, Value, _, _) :- final(Caller, State, Value),!.

alpha_beta_pruning(Caller,0, State, _, State, Value, _, _) :- eval(Caller, State, Value),!.

alpha_beta_pruning(Caller,Depth, State, Color, NewState, Value, Alpha, Beta) :-
Depth > 0,
garbage_collect,
find_states(Caller, State, Color, StatesList),
rival_color(Color, RivalColor),
NDepth is Depth - 1,
catch(
alpha_beta_pruning(Caller,StatesList, NDepth, Color, RivalColor, NewState, Value, Alpha, Beta),
_,
alpha_beta_pruning_recover(Caller,StatesList, Depth, Color, RivalColor, NewState, Value, Alpha, Beta)).

/*
* Relation: alpha_beta_pruning/8
* Searches for a move using the alpha-beta pruning algorithm with an alpha and a beta value
*/
alpha_beta_pruning(Caller,[State], Depth, _, RivalColor, State, Value, Alpha, Beta):- !,
    alpha_beta_pruning(Caller,Depth, State, RivalColor, _, Value, Alpha, Beta).

alpha_beta_pruning(Caller,[State|Rest], Depth, Color, RivalColor, NewState, Value, Alpha, Beta) :-
    alpha_beta_pruning(Caller,Depth, State, RivalColor, _, X, Alpha, Beta),
    (
        prune(Color, X, Alpha, Beta) ->
        (
            NewState = State,
            Value is X
        );
        (
            recalc(Color, X, Alpha, Beta, Nalpha, NBeta),
            alpha_beta_pruning(Caller,Rest, Depth, Color, RivalColor, B, Y, Nalpha, NBeta),
            best(Color, X, Y, State, B, NewState, Value)
        )
        
    ).

/*
* Relation: alpha_beta_pruning_recover/8
* Used to recover when an exception occurs. Updates the depth to 0 to end the search
* @1: StatesList - list of possible states
* @2: Depth - the maximum depth of the search
* @3: Color - the color of the player that moves next
* @4: RivalColor - the color of the rival
* @5: NewState - the board after applying the move selected by the search
* @6: Value - the heuristic value of the move
* @7: Alpha - the current best value for the player that tries to minimize the game value
* @8: Beta - the current best value for the player that tries to maximize the game value
*/
alpha_beta_pruning_recover(Caller,StatesList, Depth, Color, RivalColor, NewState, Value, Alpha, Beta):-
/*print_message(_, Error),*/
writef('recovered at depth %d\n', [Depth]),
garbage_collect,
alpha_beta_pruning(Caller,StatesList, 0, Color, RivalColor, NewState, Value, Alpha, Beta).


/*
* Relation: prune/4
* Succeds if a prune has to be done
* @1: Color - the color of the player that moves next
* @2: Value - the value of the move
* @3: Alpha - the current best value for the player that tries to minimize the game value
* @4: Beta - the current best value for the player that tries to maximize the game value
*/
prune(black, Value, _, Beta):-
/*writeln('pruned black'),*/
Value >= Beta.

prune(white, Value, Alpha, _):-
/*writeln('pruned white'),*/
Value =< Alpha.

/*
* Relation: recalc/6
* Recalculates alpha and beta values
* @1: Color - the color of the player that moves next
* @2: Value - the value of the move
* @3: Alpha - the current best value for the player that tries to minimize the game value
* @4: Beta - the current best value for the player that tries to maximize the game value
* @5: Nalpha - the new alpha
* @6: Nbeta - the new beta
*/
recalc(black, Value, Alpha, Beta, Nalpha, Beta):-
max_list([Alpha, Value], Nalpha).

recalc(white, Value, Alpha, Beta, Alpha, NBeta):-
min_list([Beta, Value], NBeta).

/*
* Relation: best/7
* Calculates the best value depending on the color that moves next
*/
best(black, X, Y, A, _, A, X):- X>=Y,!.
best(black, _, Y, _, B, B, Y).
best(white, X, Y, A, _, A, X):- X=<Y, !.
best(white, _, Y, _, B, B, Y).
