# API Testing Documentation

I used **Postman** to perform functional testing on the Parking Lot Management System. Below are the test cases and expected results.

## Test Case 1: Vehicle Entry (POST)
**Endpoint:** `http://localhost:8080/api/parking/entry`
**Parameters:** `vehicleNumber=AP01-1234`, `type=Car`

**Expected Result:**
- System creates a new ticket in the MySQL database.
- Response shows `isPaid: false` and a valid `entryTime`.
- HTTP Status: `200 OK`.

## Test Case 2: Vehicle Exit & Fee Calculation (POST)
**Endpoint:** `http://localhost:8080/api/parking/exit`
**Parameters:** `vehicleNumber=AP01-1234`

**Expected Result:**
- System locates the active ticket.
- System calculates the duration and applies the fee (₹20 base + ₹1/min).
- Response shows `isPaid: true` and the `totalFee` amount.
- HTTP Status: `200 OK`.
