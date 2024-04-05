| Test Case ID | Case* (+/-/edge) | Test Case Name | Precondition | Test Data | Expected Result | Result | Actual Result |
|--------------|------------------|----------------|--------------|-----------|-----------------|--------|----------------|
| TC1          | +                | Membuat order baru dengan data consumer yang sudah terdaftar | - Sudah ada data restaurant yang tersimpan
- Sudah ada data menu pada restaurant yang dipilih 
- Customer telah terdaftar | ```json
{
  "consumerId": 9,
  "deliveryAddress": {
    "city": "bandung",
    "state": "indonesia",
    "street1": "ciwaruga",
    "street2": "polban",
    "zip": "40559"
  },
  "deliveryTime": "2024-04-04T16:16:00.125Z",
  "lineItems": [
    {
      "menuItemId": "001",
      "quantity": 1
    }
  ],
  "restaurantId": 2
}
``` | Expected Result: Order akan terbuat dan pemanggilan endpoint API akan mengembalikan response berupa orderId dari order yang baru terbuat
Response: 
```json
{ "orderId": 1}
``` | PASS | Actual Result: Order akan terbuat dan pemanggilan endpoint API akan mengembalikan response berupa orderId dari order yang baru terbuat
Response:
```json
{ "orderId": 1}
``` |
| TC2          | -                | Membuat order baru dengan data consumer yang tidak terdaftar | - Sudah ada data restaurant yang tersimpan
- Sudah ada data menu pada restaurant yang dipilih | ```json
{
  "consumerId": 100,
  "deliveryAddress": {
    "city": "bandung",
    "state": "indonesia",
    "street1": "ciwaruga",
    "street2": "polban",
    "zip": "40559"
  },
  "deliveryTime": "2024-04-04T16:16:00.125Z",
  "lineItems": [
    {
      "menuItemId": "001",
      "quantity": 1
    }
  ],
  "restaurantId": 2
}
``` | Expected Result: Order akan gagal terbuat dan pemanggilan endpoint API akan mengembalikan response berupa error karena consumer tidak terdaftar
Response: 
```json
{ "timestamp": "2024-04-05T09:07:55.338+0000", "status": 500, "error": "Internal Server Error", "message": "Consumer not found with id 100", "path": "/orders"}
``` | FAIL | Actual Result: Order berhasil terbuat dan pemanggilan endpoint API akan mengembalikan response berupa orderId dari order yang baru terbuat
Response: 
```json
{ "orderId": 2}
``` |
| TC3          | +                | Membuat order baru dengan data restaurant sudah terdaftar | - Sudah ada data restaurant yang tersimpan
- Sudah ada data menu pada restaurant yang dipilih 
- Customer telah terdaftar | ```json
{
  "consumerId": 100,
  "deliveryAddress": {
    "city": "bandung",
    "state": "indonesia",
    "street1": "ciwaruga",
    "street2": "polban",
    "zip": "40559"
  },
  "deliveryTime": "2024-04-04T16:16:00.125Z",
  "lineItems": [
    {
      "menuItemId": "001",
      "quantity": 1
    }
  ],
  "restaurantId": 2
}
``` | Expected Result: Order akan terbuat dan pemanggilan endpoint API akan mengembalikan response berupa orderId dari order yang baru terbuat
Response:
```json
{
  "orderId": 3
}
``` | PASS | Actual Result: Order akan terbuat dan pemanggilan endpoint API akan mengembalikan response berupa orderId dari order yang baru terbuat
Response:
```json
{
  "orderId": 3
}
``` |
| TC4          | -                | Membuat order baru dengan data restaurant yang tidak terdaftar | - Customer telah terdaftar | ```json
{
  "consumerId": 9,
  "deliveryAddress": {
    "city": "bandung",
    "state": "indonesia",
    "street1": "ciwaruga",
    "street2": "polban",
    "zip": "40559"
  },
  "deliveryTime": "2024-04-04T16:16:00.125Z",
  "lineItems": [
    {
      "menuItemId": "001",
      "quantity": 1
    }
  ],
  "restaurantId": 100
}
``` | Expected Result: Order akan gagal terbuat dan pemanggilan endpoint API akan mengembalikan response berupa error karena restaurant tidak terdaftar
Response:
```json
{ "timestamp": "2024-04-05T09:07:55.338+0000", "status": 500, "error": "Internal Server Error", "message": "Restaurant not found with id 100", "path": "/orders"}
``` | PASS | Actual Result: Order akan gagal terbuat dan pemanggilan endpoint API akan mengembalikan response berupa error karena restaurant tidak terdaftar
Response:
```json
{ "timestamp": "2024-04-05T09:07:55.338+0000", "status": 500, "error": "Internal Server Error", "message": "Restaurant not found with id 100", "path": "/orders"}
``` |
| TC5          | +                | Membuat order dengan memilih menu yang tidak terdaftar | - Sudah ada data restaurant yang tersimpan
- Sudah ada data menu pada restaurant yang dipilih 
- Customer telah terdaftar | ```json
{
  "consumerId": 9,
  "deliveryAddress": {
    "city": "bandung",
    "state": "indonesia",
    "street1": "ciwaruga",
    "street2": "polban",
    "zip": "40559"
  },
  "deliveryTime": "2024-04-04T16:16:00.125Z",
  "lineItems": [
    {
      "menuItemId": "100",
      "quantity": 1
    }
  ],
  "restaurantId": 02
}
``` | Expected Result: Order akan gagal terbuat dan pemanggilan endpoint API akan mengembalikan response berupa error karena menu yang dipilih tidak terdaftar
Response:
```json
{ "timestamp": "2024-04-05T09:03:38.056+0000", "status": 500, "error": "Internal Server Error", "message": "Invalid menu item id 100", "path": "/orders"}
``` | PASS | Actual Result: Order akan gagal terbuat dan pemanggilan endpoint API akan mengembalikan response berupa error karena menu yang dipilih tidak terdaftar
Response:
```json
{ "timestamp": "2024-04-05T09:03:38.056+0000", "status": 500, "error": "Internal Server Error", "message": "Invalid menu item id 100", "path": "/orders"}
``` |
| TC6          | -                | Membuat order tanpa memilih menu | - Sudah ada data restaurant yang tersimpan
- Sudah ada data menu pada restaurant yang dipilih 
- Customer telah terdaftar | ```json
{
  "consumerId": 9,
  "deliveryAddress": {
    "city": "Bandung",
    "state": "Indonesia",
    "street1": "Ciwaruga",
    "street2": "Polban",
    "zip": "40559"
  },
  "deliveryTime": "2024-04-04T16:16:00.125Z",
  "lineItems": [ ],
  "restaurantId": 2
}
``` | Order akan gagal terbuat dan pemanggilan endpoint API akan mengembalikan response berupa error karena tidak ada satupun menu yang terpilih | FAIL | Actual Result: Order akan terbuat dan pemanggilan endpoint API akan mengembalikan response berupa orderId dari order yang baru terbuat
Response:
```json
{
  "orderId": 4
}
``` |
| TC7          | +                | Membuat order dengan memilih minimal satu menu | - Sudah ada data restaurant yang tersimpan
- Sudah ada data menu pada restaurant yang dipilih 
- Customer telah terdaftar | ```json
{
  "consumerId": 9,
  "deliveryAddress": {
    "city": "Bandung",
    "state": "Indonesia",
    "street1": "Ciwaruga",
    "street2": "Polban",
    "zip": "40559"
  },
  "deliveryTime": "2024-04-04T16:16:00.125Z",
  "lineItems": [
    {
      "menuItemId": "001", 
      "quantity": 2
    },
    {
      "menuItemId": "002",
      "quantity": 1
    }
  ],
  "restaurantId": 2
}
``` | Order akan terbuat dan pemanggilan endpoint API akan mengembalikan response berupa orderId dari order yang baru terbuat | PASS | Actual Result: Order akan terbuat dan pemanggilan endpoint API akan mengembalikan response berupa orderId dari order yang baru terbuat
Response:
```json
{
  "orderId": 5
}
``` |
| TC8          | +                | Membuat order dengan memilih menu dengan kuantitas minimal satu | - Sudah ada data restaurant yang tersimpan
- Sudah ada data menu pada restaurant yang dipilih 
- Customer telah terdaftar | ```json
{
  "consumerId": 9,
  "deliveryAddress": {
    "city": "Bandung",
    "state": "Indonesia",
    "street1": "Ciwaruga",
    "street2": "Polban",
    "zip": "40559"
  },
  "deliveryTime": "2024-04-04T16:16:00.125Z",
  "lineItems": [
    {
      "menuItemId": "001",
      "quantity": 5
    }
  ],
  "restaurantId": 2
}
``` | Order akan terbuat dan pemanggilan endpoint API akan mengembalikan response berupa orderId dari order yang baru terbuat | PASS | Actual Result: Order akan terbuat dan pemanggilan endpoint API akan mengembalikan response berupa orderId dari order yang baru terbuat
Response:
```json
{
  "orderId": 6
}
``` |
| TC9          | +                | Membuat order dengan memilih menu dengan kuantitas kurang dari satu | - Sudah ada data restaurant yang tersimpan
- Sudah ada data menu pada restaurant yang dipilih 
- Customer telah terdaftar | ```json
{
  "consumerId": 9,
  "deliveryAddress": {
    "city": "Bandung",
    "state": "Indonesia",
    "street1": "Ciwaruga",
    "street2": "Polban",
    "zip": "40559"
  },
  "deliveryTime": "2024-04-04T16:16:00.125Z",
  "lineItems": [
    {
      "menuItemId": "001",
      "quantity": 0
    }
  ],
  "restaurantId": 2
}
``` | Order akan gagal terbuat dan pemanggilan endpoint API akan mengembalikan response berupa error karena quantity kurang dari satu | FAIL | Actual Result: Order akan terbuat dan pemanggilan endpoint API akan mengembalikan response berupa orderId dari order yang baru terbuat
Response:
```json
{
  "orderId": 7
}
``` |
| TC10         | -                | Mengubah order dengan mengganti kuantitas dengan nilai minimal satu | - Sudah ada Order yang tersimpan | ```json
{
  "revisedOrderLineItems": [
    {
      "menuItemId": "001",
      "quantity": 3
    }
  ]
}
``` | Kuantitas dari sebuah menu akan berubah sesuai kuantitas baru yang ditentukan dan pemanggilan endpoint API akan mengembalikan response berupa perubahan yang menampilkan orderId, state, dan orderTotal
Response:
```json
{
    "orderId": 1,
    "state": "APPROVAL_PENDING",
    "orderTotal": "12.00"
}
``` | PASS | Actual Result: Kuantitas dari sebuah menu akan berubah sesuai kuantitas baru yang ditentukan dan pemanggilan endpoint API akan mengembalikan response berupa perubahan yang menampilkan orderId, state, dan orderTotal
Response:
```json
{
    "orderId": 1,
    "state": "APPROVAL_PENDING",
    "orderTotal": "12.00"
}
``` |
| TC11         | -                | Mengubah order dengan mengganti dengan kuantitas kurang dari satu | - Sudah ada Order yang tersimpan | ```json
{
  "revisedOrderLineItems": [
    {
      "menuItemId": "001",
      "quantity": -4
    }
  ]
}
``` | Kuantitas dari sebuah menu tidak akan berubah, pemanggilan endpoint API akan mengembalikan response berupa error karena kuantitas yang ditentukan kurang dari satu. | FAIL | Actual Result: Kuantitas dari sebuah menu berubah sesuai kuantitas baru yang ditentukan dimana total harga disini bisa berupa nol dan negatif, dan pemanggilan endpoint API akan mengembalikan response berupa perubahan yang menampilkan orderId, state, dan orderTotal.
Response:
```json
{
  "orderId": 1,
  "state": "APPROVED",
  "orderTotal": "-28000.00"
}
``` |
