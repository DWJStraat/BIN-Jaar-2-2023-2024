install.packages("car")
library("car")


Methode1 <- c(0.52, 0.50, 0.48, 0.40, 0.36, 0.30, 0.28, 0.28)
Methode2 <- c(0.53, 0.51, 0.48, 0.41, 0.36, 0.32, 0.30, 0.29)

# Gelijkheid van de varianties

# Shapiro Test
shapiro.test(Methode1)

# Levene Test
methode <- c(Methode1, Methode2)
length(methode)
Groep <- c(rep("Methode 1", 8), rep("Methode 2", 8))
leveneTest(methode~Groep)
