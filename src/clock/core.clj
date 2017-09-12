(ns clock.core
  (:gen-class))

(def tens-words {1 "ten"
                 2 "twenty"
                 3 "thirty"})

(def small-words {0 nil
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
                  19 "nineteen"})

(defn number-to-words
  [n]
  (cond
    (< n 20) (small-words n)
    :default (let [tens (quot n 10)
                   units (rem n 10)]
               (str (tens-words tens) (small-words  units)))))


(defn as-int [x] (read-string x))

(defn pluralise [n s]
  (if (= n 1)
    s
    (str s "s")))

(defn express-mins
  [m]
  (let [x (if (<= m 30) m (- 60 m))
        to-past (if (<= m 30) "past " "to ")]
    (cond
      (zero? x) nil
      (= 15 x) (str "quarter " to-past)
      (= 30 x) "half past "
      :def (str (number-to-words x) " " (pluralise x "minute") " " to-past))))

(defn express-hours
  [h m]
  (let [reference-hour (if  (<= m 30) h (rem (inc h) 24))
        x (if (<= reference-hour 12) reference-hour (- reference-hour 12))
        am-pm (if (< reference-hour 12) "am" "pm")]
    (cond
      (= reference-hour 0) "midnight"
      (= reference-hour 12) "noon"
      :default (str (number-to-words x) " " am-pm))))

;; 14:18 => eighteen minutes past 2 pm
(defn time-sentence
  [time]
  (let [[hours mins] (mapv as-int  (clojure.string/split time #":"))
        minute-expression (express-mins mins)
        hour-expression (express-hours hours mins)]
    (str minute-expression  hour-expression)))

(defn -main
  [& args]
  (doseq [time ["00:00"
              "14:30"
              "14:15"
              "14:45"
              "14:20"
              "14:48"
              "14:00"
              "12:00"
              "12:01"
              "00:00"
              "00:02"
              "23:59"
              "23:45"]]
    (println "at" time "we say" (time-sentence time))) 
  )
