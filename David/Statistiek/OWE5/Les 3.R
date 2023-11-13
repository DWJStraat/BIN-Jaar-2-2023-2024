qt(0.025, 9, lower.tail = FALSE)

# Monarch vlinders voorbeeld 6.1.1
n <- 14
gem_y <- 32.8143
s <- 2.4757
SE <- s/sqrt(n)
t <- qt(0.025, n-1, lower.tail = FALSE)
min <- gem_y - t *SE
max <- gem_y + t* SE