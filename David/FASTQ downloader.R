# Source: http://www.sthda.com/english/wiki/from-sra-to-fastq-file

#Read SRA file infos
sri <- read.csv("E:\\GitHub\\BIN-Jaar-2-2023-2024\\David\\SraRunInfo.csv",
                stringsAsFactors = FALSE)
files <- basename(sri$download_path)
for (i in seq_along(files)) download.file(sri$download_path[i], files[i])

# Assure that all the files has been downloaded successfully
# Remember, the R object files has been created in the previous code chunk
stopifnot(all(file.exists(files)))
for (f in files) {
  cmd <- paste("fastq-dump --split-3", f)
  cat(cmd, "\n") #print the current command
  system(cmd) # invoke command
}

