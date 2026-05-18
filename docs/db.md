# usersテーブル

| カラム | 型 | 制約 |
|--------|----|------|
| id | bigint | PK |
| email | varchar | NOT NULL, UNIQUE |
| password | varchar | NOT NULL |
| created_at | timestamp | |
| updated_at | timestamp | |
