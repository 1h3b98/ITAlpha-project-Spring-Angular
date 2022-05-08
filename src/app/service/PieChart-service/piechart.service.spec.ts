import { TestBed } from '@angular/core/testing';

import { PiechartService } from './piechart.service';

describe('PiechartService', () => {
  let service: PiechartService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(PiechartService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
