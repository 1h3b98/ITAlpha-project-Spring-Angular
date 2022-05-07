import { TestBed } from '@angular/core/testing';

import { CanloginGuardService } from './canlogin-guard.service';

describe('CanloginGuardService', () => {
  let service: CanloginGuardService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CanloginGuardService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
