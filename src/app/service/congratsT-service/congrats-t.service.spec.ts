import { TestBed } from '@angular/core/testing';

import { CongratsTService } from './congrats-t.service';

describe('CongratsTService', () => {
  let service: CongratsTService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CongratsTService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
