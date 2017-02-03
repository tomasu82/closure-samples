(def small_numbers
  ["one", "two", "three", "four", "five",
  "six","seven","eight","nine","ten",
  "eleven","twelve","thirteen","fourteen",
  "fifteen","sixteen","seventeen","eighteen",
  "nineteen"])

(def large_numbers
  ["twenty","thirty","fourty","fifty","sixty",
  "seventy","eighty","ninety"])

(defn english [number]
  (cond
    (and (< number 1000000000000) (>= number 1000000000))
      (str (english (quot number 1000000000)) " billion " (english (rem number 1000000000)))
    (and (< number 1000000000) (>= number 1000000))
      (str (english (quot number 1000000)) " million " (english (rem number 1000000)))
    (and (< number 1000000) (>= number 1000))
      (str (english (quot number 1000)) " thousand " (english (rem number 1000)))
    (and (< number 1000) (>= number 100))
      (str (english (quot number 100)) " hundred " (english (rem number 100)))
    (and (< number 100) (>= number 20))
	  (str (nth large_numbers (- (quot number 10) 2)) 
		  (if-not (zero? (rem number 10)) (str " " (english (rem number 10)))))
    (and (< number 20) (> number 0))
      (str (nth small_numbers (dec number)))))
