import csv, json, noaho

class ServiceLookup:
  def __init__(self):
    self.services = dict()
    self.index = noaho.NoAho()

    #load services.json
    json_data=open('resources/services.json')
    data = json.load(json_data)['categories']
    json_data.close()

    #load data
    for cat in data.keys():
      for obj in data[cat]:
        for name in obj.keys():
          for url in obj[name].keys():
            domains = obj[name][url]
            service = { 
              'category': cat.encode('utf8'),
              'name': name.encode('utf8'), 
              'url': url.encode('utf8')
            }
            service['domains'] = map(lambda x: x.encode('utf8'), domains)
            
            for domain in service['domains']:
              try:
                self.index.add(domain, service)  
              except Exception, e:
                #print 'Skipping {}'.format(domain)
                pass
              
  def find(self, input):
    return self.index.find_short(input)[2] or {}
    

#ask for input
default_input = 'resources/sample_input.tsv'
inputPath = raw_input('Input TSV path (default "{0}"): '.format(default_input))
if len(inputPath) == 0:
  inputPath = default_input

#ask for output
default_output = 'resources/sample_output.tsv'
outputPath = raw_input('Output TSV path (default "{0}"): '.format(default_output))
if len(outputPath) == 0:
  outputPath = default_output


lookup = ServiceLookup()

rows = []
with open(inputPath, 'rb') as inputFile:
  reader = csv.reader(inputFile, delimiter='\t')
  for row in reader:
    row.append(lookup.find(row[1]))
    rows.append(row)

with open(outputPath, 'wb') as outputFile:
  writer = csv.writer(outputFile, delimiter='\t')
  for row in rows:
    writer.writerow(row)

