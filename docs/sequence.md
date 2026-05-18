# シーケンス図

## ① ログイン処理

ユーザー  
  ↓  
View（ログイン画面 /login）  
  ↓ POST /login  
Controller（LoginController）  
  ↓  
Service（FirebaseService：認証処理）  
  ↓  
Firebase Authentication API  
  ↓  
認証結果（成功 / 失敗）  

├ 成功
│   ↓
│   Sessionにユーザー情報を保存
│   ↓
│   Controller → View
│   ↓
│   マイページ表示（/mypage）
│
└ 失敗
    ↓
    Controller → View
    ↓
    ログイン画面（エラーメッセージ表示）


---

## ② 新規登録処理

ユーザー
  ↓
View（登録画面 /signup）
  ↓ POST /signup
Controller（SignupController）
  ↓
Service（FirebaseService：ユーザー登録）
  ↓
Firebase Authentication API
  ↓
登録結果（成功 / 失敗）

├ 成功
│   ↓
│   Controller → View
│   ↓
│   ログイン画面へ遷移
│
└ 失敗
    ↓
    Controller → View
    ↓
    登録画面（エラーメッセージ表示）


---

## ③ ログアウト処理

ユーザー
  ↓
View（マイページ /mypage）
  ↓ GET /logout
Controller
  ↓
Sessionを破棄（invalidate）
  ↓
Controller → View
  ↓
ログイン画面へ遷移


---

## ④ マイページ表示（認証チェック）

ユーザー
  ↓
GET /mypage
Controller
  ↓
Session確認

├ ログイン済み
│   ↓
│   View（マイページ）表示
│
└ 未ログイン
    ↓
    リダイレクト
    ↓
    ログイン画面へ (/login)
``
