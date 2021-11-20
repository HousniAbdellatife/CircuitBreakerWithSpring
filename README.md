# CircuitBreakerWithSpring
The problem:
A service generally calls other services to retrieve data, and there is chance tahr downstream service may be down
1) Exhausting network resources and slowing performances.
2) The user experience will be bad and unpredictible.
3) The failure of one service can potentially cascade to other services throughout the application.

resilience4j documentation for more information: https://resilience4j.readme.io/docs/circuitbreaker

some important informations about CircuitBreaker :

each CircuitBreaker has three essentials states : CLOSED, OPEN, and HALF_OPEN
The CircuitBreaker uses a sliding window to store and aggregate the outcome of calls
there is two types of sliding window : COUNT_BASED and TIME_BASE
Resilience4j comes with an in-memory CircuitBreakerRegistry based on a ConcurrentHashMap which provides thread safety and atomicity guarantees.



some important concepts about CircuitBreaker :

failureRateThreshold (in percentage)
slowCallRateThreshold, slowCallDurationThreshold
permittedNumberOfCallsInHalfOpenState
slidingWindowType, slidingWindowSize, minimumNumberOfCalls
automaticTransitionFromOpenToHalfOpenEnabled, waitDurationInOpenState
    