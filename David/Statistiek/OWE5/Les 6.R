north <- c(22,17,13, 14,22, 19,22,14)
east <-c(20,17, 12,20, 22, 17,20,19)
south <-c(24, 22,20, 18,18, 20,15,23)
west <-c(26, 16, 20,16, 14,12, 22,14)
open <-c(17, 12,16, 17,16,11, 12,14)

H0 <- "De gemiddelde steellengte is niet verschillend tussen de
verschillende locaties"
HA <- "De gemiddelde steellengte is verschillend tussen de verschillende
locaties"

lengte <- c(north, east, south, west, open)

gebied <- c(rep("north", 8), rep("east", 8), rep("south", 8), rep("west", 8),
            rep("open", 8))

aov <- summary(aov(lengte ~ gebied))
p <- aov[[1]][["Pr(>F)"]] [[1]]
alpha <- 0.05

if (p < alpha) {
  print(HA)
} else {
  print(H0)
}