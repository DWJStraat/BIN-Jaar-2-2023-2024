import advertools as adv
import jl
adv.crawl(input('Enter a URL: '), 'my_output_file.jl',
          follow_links=True)

output = jl.load(open('my_output_file.jl'))