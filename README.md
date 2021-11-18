# CircuitBreakerWithSpring
The problem:
A service generally calls other services to retrieve data, and there is chance tahr downstream service may be down
1) Exhausting network resources and slowing performances.
2) The user experience will be bad and unpredictible.
3) The failure of one service can potentially cascade to other services throughout the application.