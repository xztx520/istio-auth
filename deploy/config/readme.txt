0.0.5.0
platform-auth-jwt.yaml和platform-auth-rbac.yaml只在新环境部署平台时候执行一次，
后续更新platform-auth服务，不再更新执行platform-auth-jwt.yaml和platform-auth-rbac.yaml
0.0.6.0
1. 新增配置参数 ：
is_secret_key: false
secret_key: 123456