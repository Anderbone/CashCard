package example.cashcard;

import java.net.URI;
import java.util.Optional;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/cashcards")
public class CashCardController {
	private CashCardRepository cashCardRepository;
	
	public CashCardController(CashCardRepository cashCardRepository) {
		this.cashCardRepository = cashCardRepository;
	}
	
	@GetMapping("/{requestedId}")
	public ResponseEntity<CashCard> findById(@PathVariable Long requestedId) {
		
		Optional<CashCard> cashCardOptional = cashCardRepository.findById(requestedId);
		
		return cashCardOptional.map(ResponseEntity::ok).orElseGet(()->ResponseEntity.notFound().build());
		
	}
	@PostMapping
	private ResponseEntity<Void> createCashCard(@RequestBody CashCard newCashCardRequest, UriComponentsBuilder ucb) {
		CashCard savedCashCard = cashCardRepository.save(newCashCardRequest);
		URI locationOfNewCashCard = ucb
				.path("cashcards/{id}")
				.buildAndExpand(savedCashCard.id())
				.toUri();
		return ResponseEntity.created(locationOfNewCashCard).build();
	}
}