import { TestBed } from '@angular/core/testing';

import { TropheyService } from './trophey.service';

describe('TropheyService', () => {
  let service: TropheyService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(TropheyService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
