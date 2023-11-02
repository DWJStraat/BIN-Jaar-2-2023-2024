konijnen_vel <- c(62, 60, 59, 61, 60, 58, 59, 60, 57, 56, 59, 58, 60, 59, 57)
konijnen_duin <- c(58, 59, 57, 59, 59, 57, 55, 60, 57, 58, 59, 58, 57, 58, 59)

t_test <- t.test(konijnen_vel, konijnen_duin, paired = FALSE,
       alt = "two.sided", var.equal = TRUE)

p <- t_test$p.value

h0 <- "Het medicijn heeft geen effect op bloeddruk"
ha <- "Het medicijn verlaagt de bloeddruk"
alpha <- 0.05
bloed_voor <- c(150, 170, 130, 155, 165, 135, 140, 140, 130, 160, 150, 150)
bloed_na <- c(120, 160, 130, 110, 110, 140, 145, 130, 130, 120, 155, 130)
t.test(bloed_na, bloed_voor, paired = TRUE,
       alt = "less", var.equal = TRUE)
bloed_t <- t.test(bloed_na, bloed_voor, paired = TRUE,
                  alt = "less", var.equal = TRUE)
bloed_p <- bloed_t$p.value
print(bloed_p)
if(bloed_p > alpha) {
  print(h0)
} else {
  print(ha)
}