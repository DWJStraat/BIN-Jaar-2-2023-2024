dieet_1 <- c(5.5, 5.4, 6.1, 5.9)
dieet_2 <- c(5.4, 5.8, 5.3, 6.8)
dieet_3 <- c(6.1,6.3,6.7,7.1)
dieet_4 <- c(5.9,5.8,6.1,6.5)

dieet <- c(dieet_1, dieet_2, dieet_3, dieet_4)

groepen <- c(rep("dieet_1", 4), rep("dieet_2", 4),
             rep("dieet_3", 4), rep("dieet_4", 4))

summary(aov(dieet ~ groepen))


pop_a <- c(32, 31, 31, 30)
pop_b <- c(32, 34, 34, 33)

t.test(pop_a, pop_b)

bloed_voor <- c(92, 95, 97, 90, 105, 98, 90, 108)
bloed_na   <- c(84, 88, 85, 87, 92 , 82, 92, 90)

t.test(bloed_na, bloed_voor, paired = TRUE, alt="less", conf.level = 0.99)

methode_1 <- c(13, 11, 21, 8, 7, 35, 16, 29, 5, 12)
methode_2 <- c(11, 12, 20, 9, 9, 32, 14, 28, 6, 11)

t.test(methode_1, methode_2, paired = TRUE)

ph <- c(9.20, 9.18, 9.05, 9.11, 9.10, 9.08)
average_ph <- mean(ph)

t.test(ph, mu = average_ph)