package hackernews;

import java.time.Instant;
import java.time.ZoneOffset;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;

import graphql.GraphQLException;
import graphql.schema.DataFetchingEnvironment;

public class Mutation implements GraphQLMutationResolver {

	private final LinkRepository linkRepository;
	private final UserRepository userRepository;
	private final VoteRepository voteRepository;

	public Mutation(LinkRepository linkRepository, UserRepository userRepository, VoteRepository voteRepository) {
		this.linkRepository = linkRepository;
		this.userRepository = userRepository;
		this.voteRepository = voteRepository;
	}

	public Link createLink(String url, String description, DataFetchingEnvironment env) {
		AuthContext authContext = env.getContext();
		Link newLink = new Link(url, description, authContext.getUser().getId());
		linkRepository.saveLink(newLink);
		return newLink;
	}

	public User createUser(String name, AuthData auth) {
		User newUser = new User(name, auth.getEmail(), auth.getPassword());
		return userRepository.saveUser(newUser);
	}

	public Vote createVote(String linkId, String userId) {
		Vote vote = new Vote(Instant.now().atZone(ZoneOffset.UTC), userId, linkId);
		return voteRepository.saveVote(vote);
	}

	public SigninPayload signinUser(AuthData auth) throws IllegalAccessException {
		User user = userRepository.findByEmail(auth.getEmail());
		if (user.getPassword().equals(auth.getPassword())) {
			return new SigninPayload(user.getId(), user);
		}
		throw new GraphQLException("Invalid Credentials");
	}
}
