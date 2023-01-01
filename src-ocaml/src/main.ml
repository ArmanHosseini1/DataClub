open Libunix
open Libcsv
(* Définit une autre procédure - programme lisant/écrivant un CSV *)
let main_csv_demo () =
  let chemin = Libunix.get_example_file "TFC-matchs.csv" in
  let output = Libunix.get_example_file "TFC-matchs-vf.csv" in
  let csv = Libcsv.load_csv chemin in
  let csv' = Libcsv.map_csv (fun s -> s ^ "#TFC2022") csv in
  let nl, nc = Libcsv.lines_columns csv' in
  let () = Format.printf "Ecriture d'un CSV de taille (%d x %d) dans: %s\n" nl nc output in
  Libcsv.save_csv output csv'

(* Exécute les procédures précédentes *)

let () = main_csv_demo ()