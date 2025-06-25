This document outlines the changes introduced by PR #22, which adds new endpoints for managing Product Categories.

---

# Product Category API Endpoints

This PR introduces a new set of endpoints to manage product categories within the system. These endpoints allow for the creation, retrieval, update, and deletion of product categories, enabling better organization and filtering of products.

**Base URL:** `https://api.example.com/v1` (assuming a versioned API)

## 1. New Endpoints

### `GET /categories` - List All Product Categories

*   **Description:** Retrieves a paginated list of all product categories.
*   **Authentication:** Required
*   **Rate Limiting:** Applied

#### Parameters

| Name    | Type    | In     | Description                                | Required | Default |
| :------ | :------ | :----- | :----------------------------------------- | :------- | :------ |
| `page`  | `integer` | `query`  | The page number to retrieve.               | No       | `1`     |
| `limit` | `integer` | `query`  | The number of items per page (max 100).    | No       | `20`    |
| `name`  | `string`  | `query`  | Filter categories by name (case-insensitive, partial match). | No       |         |

#### Response Format (200 OK)

```json
{
  "data": [
    {
      "id": 1,
      "name": "Electronics",
      "description": "Gadgets, devices, and electronic accessories.",
      "parent_id": null,
      "created_at": "2023-10-26T10:00:00Z",
      "updated_at": "2023-10-26T10:00:00Z"
    },
    {
      "id": 2,
      "name": "Laptops",
      "description": "Portable computers for work and play.",
      "parent_id": 1,
      "created_at": "2023-10-26T10:05:00Z",
      "updated_at": "2023-10-26T10:05:00Z"
    }
  ],
  "meta": {
    "total": 2,
    "page": 1,
    "limit": 20,
    "has_more": false
  }
}
```

#### Example Usage

```bash
curl -X GET \
  'https://api.example.com/v1/categories?page=1&limit=10&name=elec' \
  -H 'Authorization: Bearer YOUR_ACCESS_TOKEN'
```

---

### `GET /categories/{id}` - Retrieve a Single Product Category

*   **Description:** Retrieves the details of a specific product category by its unique ID.
*   **Authentication:** Required

#### Parameters

|