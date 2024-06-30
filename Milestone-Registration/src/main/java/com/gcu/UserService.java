package com.gcu;

public class UserService implements UserInterface {

	@Autowired
    	private UserRepository userRepository;
	
	@Override
	public void register(UserModel model) {
		// TODO Auto-generated method stub
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		System.out.println("Initialize");
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		System.out.println("Destroy");
	}

   	public User registerUser(UserModel user) {
        	return userRepository.save(user);
    	}

    	public User findByUsername(String username) {
        	return userRepository.findByUsername(username);
    	}

}
