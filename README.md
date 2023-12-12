In this project /api/users/register is available for everyone <br>
 to get all the user details /api/users is available for only Admin user <br>
 to get single user details by id /api/users/{id} is available for Admin and User both <br>
 to delete the user /api/users/{id} is available for Admin only <br>
 /api/test/admin is available for Admin only <br>
 /api/test/user is available for Admin and User both  <br>
 /api/test/any is available for everyone <br>
 Note: Here i have used hasAuthority("") method not hasRole(""). hence ROLE_ prefix is not required <br>
