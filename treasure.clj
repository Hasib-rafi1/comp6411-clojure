(ns a2.treasure)
(defn printResult[map_details result]
			(if(= result 0)
					(do 
							(println "\n Woo hoo, I found the treasure :-):\n")
					)
					(do
							(println "\n Uh oh, I could not find the treasure :-(\n")
					)

			)

			(doseq [item2 map_details]
					(println item2)
				)
			(System/exit 0)
)

;finding path
(defn FindPath [map_details index_x index_y col_count call_count]
				(def row_count (- (count map_details) 1))	
				(def line (get map_details index_x))
				(def charecter(nth(seq line) index_y))
				(def ^:dynamic total)
				(if(= "@" (str charecter))
						(printResult map_details 0)
				)
				(if(= "-" (str charecter))
							(do
										(def first_part (subs line 0 index_y))
										(if(= index_y col_count)
											(do 

													(binding  [total (str first_part "+")])
											)
											(do
													(def sec_part (subs line (+ index_y 1)))
													(def total (str first_part "+" sec_part))
											)
										)
										;(println total)
										(def a(assoc map_details index_x total))
										(if (> index_y 0)
														(do 
																(def y (- index_y 1))
																(FindPath a index_x y col_count 1)
														)
										)
										(if (< index_y col_count)
														(do 
																(if (= index_x row_count)
																		(do (if(= index_y (- col_count 1))
																								(do

																								)
																								(do
																											(def y (+ index_y 1))
																											(FindPath a index_x y col_count 1)
																								)
																						)

																		)
																		(do
																					(def y (+ index_y 1))
																					(FindPath a index_x y col_count 1)
																		)
																)
														)
										)
										(if (> index_x 0)
														(do 
																(def x (- index_x 1))
																(FindPath a x index_y col_count 1)
														)
										)
										(if (< index_x row_count)
														(do 
																(def x (+ index_x 1))
																(FindPath a x index_y col_count 1)
														)
										)
										(def line (get a index_x))
										(def first_part (subs line 0 index_y))
										(def sec_part (subs line (+ index_y 1)))
										(def total (str first_part "!" sec_part))
										(def a(assoc a index_x total))

										(if(= call_count 0)
											(do
													(printResult a 1)
											)
										)
										;(FindPath a 1)

							)
							(do
									;(println "fail")
							)
				)		
)




;a function for reading the value and printing as like in the text
(defn MapPrepare []
				(def map_data (slurp "map.txt"))
   	(println "This is my challenge:\n")
				(def map_list (clojure.string/split map_data #"\n"))
				(def col_count (- (count(get map_list 0)) 1))
				(doseq [item map_list]
									;(println (count item))
									(if (= col_count (count (clojure.string/trim-newline item)))
									(do 
   							(println item))
									(do (println "Map has a problem")
										(System/exit 0)
									)
									))
				(FindPath map_list 0 0 col_count 0)
				(println(count map_list))
				)
(MapPrepare)

