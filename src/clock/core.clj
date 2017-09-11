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

(defn as-int [x] (read-string x))

(defn express-mins
  [m]
  (let [
        x (if (<= m 30) m (- 60 m))
        to-past (if (<= m 30) "past" "to")
        ]
    (if (zero? x) ""
        (str (number-to-words x) " minutes " to-past )))
  )

(defn express-hours
  [h m]
  (let [
        reference-hour (if  (<= m 30) h (inc h))
        x (if (<= reference-hour 12) reference-hour (- reference-hour 12))
        am-pm (if (< reference-hour 12) "am" "pm")]
     (str x " " am-pm)
    )
  )

;; 14:18 => eighteen minutes past 2 pm
(defn time-sentence
  [time]
  (let [[hours mins] (mapv as-int  (clojure.string/split time #":"))
        minute-expression (express-mins mins)
        hour-expression (express-hours hours mins)
        ]


    [hours mins minute-expression hour-expression])




  )

(time-sentence "14:18")

(time-sentence "14:48")

(time-sentence "14:00")



(defn -main
  [& args]
  (println "Hello, World!")

  )
