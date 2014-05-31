#!/usr/bin/env python
import sys, json

f = open('/Users/mio6si/Dropbox/6Sense/Crunchbase/crunchbase_merged.txt','r')
g = open('more_crunchbase.csv','w')
g.write("name\turl\tcategory\tcity\tcountry\n")
for line in f:
	try:
		data = json.loads(line)
		url = data['homepage_url']
		name = data['name']
		city = data['offices'][0]['city']
		country = data['offices'][0]['country_code']
		category = data['category_code']
		g.write("%s\t%s\t%s\t%s\t%s\n" %(name, url, category, city, country))
	except:
		continue
f.close()
g.close()