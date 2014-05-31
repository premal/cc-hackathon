

To Compute Similarities

1. Start with json of the form

{ company : { company's technologies }}

2. Form the full cross product. Call this file P.

3. For every line of P

{ company_A : {A's technologies}, company_B : {B's technologies}}

we want to emit

company_A, company_B, # of common technologies, similarity score = # common technologies / total # technologies

This way we get a score and a raw measure (# common technologies) of how similar they are, too.

Should we compute similarity scores based on other characteristics? Or use this measure of similarity and we can filter by or surface other characteristics (like tags, company type) in the UI?


