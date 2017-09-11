(ns clock.core
  (:gen-class))


(def tens-words {
           1 "ten"
           2 "twenty"
           3 "thirty"
                 })

(def small-words {
                  0 "zero"
           1 "one"
           2 "two"
            3 "three"
            4 "four"
            5 "five"
            6 "six"
            7 "seven"
            8 "eight"
            9 "nine"
            10 "ten"
            11 "eleven"
            12 "twelve"
            13 "thirteen"
            14 "fourteen"
            15 "fifteen"
            16 "sixteen"
            17 "seventeen"
            18 "eighteen"
            19 "nineteen"
            })

(defn number-to-words
  [n]
  (cond
    (< n 20) (small-words n)
    :default (let [tens (quot n 10)
          units (rem n 10)]
               (str (tens-words tens) (small-words  units)))
    )
  )

(number-to-words 3)
(number-to-words 10)
(number-to-words 13)
(number-to-words 23)
(number-to-words 30)
(number-to-words 0)


(defn -main
  [& args]
  (println "Hello, World!")

  )
