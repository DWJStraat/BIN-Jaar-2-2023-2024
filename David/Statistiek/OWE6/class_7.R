age_tumor <- read.table(file.choose(), header = TRUE, sep=";")
attach(age_tumor)
View(head(age_tumor))

# Correlation coefficient
# cor(x, y)
cor(Age, Tumor_Size)

# p-value
# cor.test(x, y)$p.value
cor.test(Age, Tumor_Size)$p.value

# H0 = Age & Tumor size do not correlate in the population
# HA = Age & Tumor size do correlate in the population
# Alpha = 0.05
# p < alpha, so we reject H0
# There is enough evidence to conclude that Age & Tumor size correlate in
# the population

# Regression
# lm(y ~ x)
summary(lm(Tumor_Size ~ Age))


# Example exercise

