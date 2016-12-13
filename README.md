Interview Repo for MFG

# To reply this interview please make a pull request on this repo

# Task to do
* Create a object Good with the following attributes that will be saved in mongoDB:
  * name: the name of the Good, this name must be unique, not null and no more than 50 characters
  * age: the age of the Good, must not be under zero
  * productionDate: the date the Good was product
* Create API to manage the Good object:
  * GET /api/good : get the list of 10 most recent Goods, then can access next one by /api/good?page=1&size=10 
  * POST /api/good: post by json in body a new Good to create it
  * PUT /api/good: put by json in body an existing Good to update it
  * DELETE /api/good/{id}: delete a Good by it's id
  * GET /api/good/{id}: get a good by it's id
  
  test
