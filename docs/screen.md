# 画面遷移図

/login（ログイン画面）
    ↓ POST /login
    ↓ 認証成功
/mypage（ユーザー画面）

/signup（登録画面）
    ↓ POST /signup
    ↓ 登録成功
/login

/mypage
    ↓ GET /logout
/login
