package example.cashcard;

import java.util.Optional;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
}