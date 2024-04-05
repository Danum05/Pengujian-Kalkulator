| Test Case ID | Case* (+/-/edge) | Test Case Name | Precondition | Test Data | Expected Result | Result | Actual Result |
|--------------|-------------------|----------------|--------------|-----------|-----------------|--------|----------------|
| TC1          | +                 | Membuat order baru dengan data consumer yang sudah terdaftar | - Sudah ada data restaurant yang tersimpan - Sudah ada data menu pada restaurant yang dipilih - Customer telah terdaftar | ```json
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
``` | Expected Result: Order akan terbuat dan pemanggilan endpoint API akan mengembalikan response berupa orderId dari order yang baru terbuat Response: { "orderId": 1} | PASS | Actual Result: Order akan terbuat dan pemanggilan endpoint API akan mengembalikan response berupa orderId dari order yang baru terbuat Response: { "orderId": 1} |
| TC2          | +                 | Membuat order baru dengan data consumer yang tidak terdaftar | - Sudah ada data restaurant yang tersimpan - Sudah ada data menu pada restaurant yang dipilih | ```json
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
``` | Expected Result: Order akan gagal terbuat dan pemanggilan endpoint API akan mengembalikan response berupa error karena consumer tidak terdaftar Response: { "timestamp": "2024-04-05T09:07:55.338+0000", "status": 500, "error": "Internal Server Error", "message": "Consumer not found with id 100", "path": "/orders"} | FAIL | Actual Result: Order berhasil terbuat dan pemanggilan endpoint API akan mengembalikan response berupa orderId dari order yang baru terbuat Response: { "orderId": 2} |
| TC3          | +                 | Membuat order baru dengan data restaurant sudah terdaftar | - Sudah ada data restaurant yang tersimpan - Sudah ada data menu pada restaurant yang dipilih - Customer telah terdaftar | ```json
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
``` | Expected Result: Order akan terbuat dan pemanggilan endpoint API akan mengembalikan response berupa orderId dari order yang baru terbuat Response: { "orderId": 3} | PASS | Actual Result: Order akan terbuat dan pemanggilan endpoint API akan mengembalikan response berupa orderId dari order yang baru terbuat Response: { "orderId": 3} |
| TC4          | +                 | Membuat order baru dengan data restaurant yang tidak terdaftar | - Customer telah terdaftar | ```json
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
``` | Expected Result: Order akan gagal terbuat dan pemanggilan endpoint API akan mengembalikan response berupa error karena restaurant tidak terdaftar Response: { "timestamp": "2024-04-05T09:07:55.338+0000", "status": 500, "error": "Internal Server Error", "message": "Restaurant not found with id 100", "path": "/orders"} | PASS | Actual Result: Order akan gagal terbuat dan pemanggilan endpoint API akan mengembalikan response berupa error karena restaurant tidak terdaftar Response: { "timestamp": "2024-04-05T09:07:55.338+0000", "status": 500, "error": "Internal Server Error", "message": "Restaurant not found with id 100", "path": "/orders"} |
| TC5          | +                 | Membuat order dengan memilih menu yang tidak terdaftar | - Sudah ada data restaurant yang tersimpan - Sudah ada data menu pada restaurant yang di
