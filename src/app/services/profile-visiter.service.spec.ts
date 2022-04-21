import { TestBed } from '@angular/core/testing';

import { ProfileVisiterService } from './profile-visiter.service';

describe('ProfileVisiterService', () => {
  let service: ProfileVisiterService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ProfileVisiterService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
