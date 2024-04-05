| Test Case ID | Case* (+/-/edge) | Test Case Name | Precondition | Test Data | Expected Result | Result | Actual Result
|--------------|------------------|----------------|--------------|-----------|-----------------|--------|---------------
| TC1          | +                | Membuat order baru dengan data consumer yang sudah terdaftar | - Sudah ada data restaurant yang tersimpan
                                                                                   - Sudah ada data menu pada restaurant yang dipilih 
                                                                                   - Customer telah terdaftar | {
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
                                                                                                                    } | Expected Result:
                                                                                                                          Order akan terbuat dan pemanggilan endpoint API akan mengembalikan response berupa orderId dari order yang baru terbuat

                                                                                                                        Response: 
                                                                                                                        { "orderId": 1} | PASS   | Actual Result:
                                                                                                                          Order akan terbuat dan pemanggilan endpoint API akan mengembalikan response berupa orderId dari order yang baru terbuat

                                                                                                                        Response:
                                                                                                                        { "orderId": 1}
| TC2          | +                | Membuat order baru dengan data consumer yang tidak terdaftar | - Sudah ada data restaurant yang tersimpan
                                                                                        - Sudah ada data menu pada restaurant yang dipilih | {
                                                                                                                                          "consumerId": 100,
                                                                                                                                          "deliveryAddress": {
                                                                                                                                            "city": "bandung",
                                                                                                                                            "state": "indonesia",
                                                                                                                                            "street1": "ciwaruga",
                                                                                                                                            "street2": "polban",
                                                                                                                                            "zip": "
