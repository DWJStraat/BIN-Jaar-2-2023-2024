Growth <- c(17, 20, 22, 64, 170, 190, 315)
Gap <- c(6, 13, 14,15, 16, 18, 22, 29)

wilcox.test(Growth, Gap, exact = FALSE, alternative = "two.sided")

wilcox.test(Growth, Gap, exact = FALSE, alternative = "greater")
