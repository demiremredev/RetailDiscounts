Problem: 

On a retail website, the following discounts apply:
1. If the user is an employee of the store, he gets a 30% discount
2. If the user is an affiliate of the store, he gets a 10% discount
3. If the user has been a customer for over 2 years, he gets a 5% discount.
4. For every $100 on the bill, there would be a $ 5 discount (e.g. for $ 990, you get $ 45
as a discount).
5. The percentage based discounts do not apply on groceries.
6. A user can get only one of the percentage based discounts on a bill.


My algorithm for solution:

First of all, I applied percentage based discounts before 5$ discounts. 
I mean calculation is like that:

For example I am an AFFILIATE, so percentage should be %10.

The products for example: 

sum =     (2 x Product1 x 0,9)
        + (2 x Product2 x 0,9)
        + (2 x Product3 x 0,9)
        + (2 x GroceryProduct)  

as you can see percentage based discount not applied to grocery product.

after that I check the sum value. For example sum is 430$
then it should be applied 20$ additional discount.

Here is the formula: 

Math.floor(sum / 100) => 4 

mvn -Dtest=RetailStoreDiscountsApplicationTest test

