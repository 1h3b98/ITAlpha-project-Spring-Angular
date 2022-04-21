import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProfileVisiterComponent } from './profile-visiter.component';

describe('ProfileVisiterComponent', () => {
  let component: ProfileVisiterComponent;
  let fixture: ComponentFixture<ProfileVisiterComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ProfileVisiterComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ProfileVisiterComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
