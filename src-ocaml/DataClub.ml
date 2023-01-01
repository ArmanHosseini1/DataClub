(* Ceci est un éditeur pour OCaml
   Entrez votre programme ici, et envoyez-le au toplevel en utilisant le
   bouton "Évaluer le code" ci-dessous ou [Ctrl-e]. *)


let rec count_goals equipe l (* string list list *) = match l with
  |[] -> 0
  |s::l1 -> let is_equipe equipe l = match l with
      |[] -> false
      |m::l2 -> if (m==equipe) then true else false
      in if (is_equipe equipe s) then let rec check_goal l  = match l with
          |[] -> 0
          |m::l3 -> if (m=="%but") then 1 else check_goal l3 in ((check_goal s) + (count_goals equipe l1)) else (count_goals equipe l1);;
        
let afficher_score equipe1 equipe2 l = (string_of_int(count_goals equipe1 l))^" - "^(string_of_int(count_goals equipe2 l));;
                                                                                    
let rec avant_match equipe1 equipe2 matchs = match matchs with
  |[] -> ""
  |m1::m -> let premier_element liste = match liste with
      |e1::liste -> e1
      |_ -> "" in 
      let avant_match_2 equipe1 equipe2 game = match game with
        |e1::e2::game -> if ((((premier_element e1)==equipe1) && ((premier_element e2)==equipe2)) || (((premier_element e2)==equipe1) && ((premier_element e1)==equipe2))) then (afficher_score equipe1 equipe2 game) else "" 
        |_-> ""
      in (avant_match_2 equipe1 equipe2 m1)^" -;- "^(avant_match equipe1 equipe2 m);;
                                                                                                   
let()= print_int 3;;

(* = ou == *)

let rec best_players equipe (* string list*) matchs (* string string list *) = match equipe with
  |[] -> ""
  |s::equipe2 -> let rec length matchs = match matchs with
      |[] -> 0
      |m1::m2 -> 1 + length m2 in let rec best_players_2 joueur matchs = match matchs with
      |[] -> 0
      |m::matchs2 -> let rec find_joueurs joueur m = match m with
          |[] -> false
          |m1::m2 -> if (m1==joueur) then true else find_joueurs joueur m2 in let rec count_goal a m = match m with
          |[] -> 0
          |m1::m2 -> if (m1==("But "^a)) then 1 + count_goal a m2 else count_goal a m2 
          in let gagne m = ((count_goal "1" m) > (count_goal "2" m))
          in if ((find_joueurs joueur m) && (gagne m)) then 1+(best_players_2 joueur matchs2) else (best_players_2 joueur matchs2)
      in if ((best_players_2 s matchs)>((length matchs)*0.4)) then s^"-:-"^(best_players equipe2 matchs) else (best_players equipe2 matchs);;
                  