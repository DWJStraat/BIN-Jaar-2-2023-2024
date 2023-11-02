# Opdracht 2 C
dieet_1 <- c(5.5, 5.4, 5.1, 5.9)
dieet_2 <- c(5.4, 5.8, 5.3, 6.8)
dieet_3 <- c(6.1, 6.3, 5.7, 7.1)
dieet_4 <- c(5.9, 5.8, 6.1, 6.5)

dieet <- c(dieet_1, dieet_2, dieet_3, dieet_4)
groepen <- c(rep("dieet_1", 4), rep("dieet_2", 4), rep("dieet_3", 4),
             rep("dieet_4", 4))
summary(aov(dieet ~ groepen))

# Opdracht 3
ATpercentA <- c(32, 31, 31, 30)
ATpercentB <- c(32, 34, 34, 33)
t.test(ATpercentA, ATpercentB)