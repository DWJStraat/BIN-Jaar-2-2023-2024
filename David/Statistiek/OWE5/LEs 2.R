avg_fish_length <- 54
sd_fish_length <- 4.5

# Percentage of fish between 51.0 and 60.0
a <- pnorm(60, avg_fish_length, sd_fish_length) - pnorm(51,
                                                        avg_fish_length,
                                                        sd_fish_length)
a.rounded <- round(a * 100, 2)

sample_size <- 4
# Chance that all fish in the sample size are between 51.0 and 60.0
b <- a^sample_size
b.rounded <- round(b, 4)

# Chance that the average size of the fish in the sample size is between 51.0
# and 60.0
c <- pnorm(60, avg_fish_length, sd_fish_length / sqrt(sample_size)) -
  pnorm(51, avg_fish_length, sd_fish_length / sqrt(sample_size))
c.rounded <- round(c, 4)

# If e represents the average length of a random sample of 4 fish, what is
# Pr{50,0 < e < 58,0}?
d <- pnorm(58, avg_fish_length, sd_fish_length / sqrt(sample_size)) -
  pnorm(50, avg_fish_length, sd_fish_length / sqrt(sample_size))
d.rounded <- round(d, 4)

a.rounded
b.rounded
c.rounded
d.rounded