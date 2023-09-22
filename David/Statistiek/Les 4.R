lengte_vissen_filmpje <- c(33,34,41,52,43,52,43,32,30)

p <- t.test(lengte_vissen_filmpje, alt = "greater", mu = 35)
alpha <- 0.05

if(p$p.value > alpha) {
  print("Geen bewijs voor de stelling")
} else {
  print("Wel bewijs voor de stelling")
}

quick_t <- function(c, alpha, alt, mu) {
  p <- t.test(c, alt = alt, mu = mu)
  print(p$p.value)
  if(p$p.value > alpha) {
    print("Geen bewijs voor de stelling")
  } else {
    print("Wel bewijs voor de stelling")
  }
}

lengte_vissen_filmpje <- c(36,34,41,52,47,52,43,32,30)

quick_t(lengte_vissen_filmpje, 0.05, "greater", 35)


time_spent_sequuencing <- c(310,306,299,329,311,294,301)

quick_t(time_spent_sequuencing, 0.05, "greater", 300)