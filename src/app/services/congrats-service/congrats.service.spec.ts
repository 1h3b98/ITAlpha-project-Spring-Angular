import { TestBed } from '@angular/core/testing';

import { CongratsService } from './congrats.service';

describe('CongratsService', () => {
  let service: CongratsService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CongratsService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
